#!/usr/bin/env sh

SCRIPT_FILE_PATH=$(readlink -f "$0")
SCRIPT_DIR_PATH=$(dirname "$SCRIPT_FILE_PATH")

# Clones the Provisioner source code inside a temporary directory
PROVISIONER_SOURCES_DIR_PATH=$(mktemp -d)
if [ $? -ne 0 ]; then
	>&2 echo "Unable to create temporary directory"
	exit 1
fi
if ! git clone \
	--depth=1 \
	https://github.com/kubernetes-sigs/sig-storage-local-static-provisioner.git \
	"$PROVISIONER_SOURCES_DIR_PATH" \
	; then

	>&2 echo "Unable to clone the Local Volume Provisioner git repository"
	exit 1
fi

# Installs the Provisioner into our K8S cluster
if ! helm install \
	-f "$SCRIPT_DIR_PATH/lvp-values.yaml" \
	local-volume-provisioner \
	"$PROVISIONER_SOURCES_DIR_PATH/helm/provisioner" \
	; then

	>&2 echo "Unable to install the Local Volume Provisioner into the K8S cluster using Helm"
	exit 1
fi

# Removes the Provisioner source code
if ! rm -rf "$PROVISIONER_SOURCES_DIR_PATH"; then
	>&2 echo "Unable to delete Local Volume Provisioner source code"
	exit 1
fi
