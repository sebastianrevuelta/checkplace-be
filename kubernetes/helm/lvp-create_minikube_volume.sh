#!/usr/bin/env sh

VOLUMES_DIR_PATH="/mnt/k8s"

SCRIPT_BASE_NAME=$(basename "$0")
DRY_RUN=0
SEE_HELP_MSG="See \`$SCRIPT_BASE_NAME -h\`"

print_usage() {
    echo "Creates a new volume folder inside your minikube node that will be automatically detected by LVP (Local Volume Provisioner)"
    echo ""
    echo "Usage:"
    echo "  $SCRIPT_BASE_NAME -h"
    echo "  $SCRIPT_BASE_NAME [-d] volume_name"
    echo ""
    echo "Options:"
    echo "  -h   Shows this help message"
    echo "  -d   Dry-run. Shows the command, but does not run it"
    echo ""
    echo "Examples:"
    echo "  $SCRIPT_BASE_NAME -h"
    echo "  $SCRIPT_BASE_NAME -d myvolume"
}

print_error() {
    >&2 echo "$SCRIPT_BASE_NAME: $1"
}

while getopts ":hd" option; do
    case $option in
        h)
            print_usage
            exit 0
            ;;
        d)
            DRY_RUN=1
            ;;
        *)
            print_error "Unknown option '$OPTARG'. $SEE_HELP_MSG"
            exit 1
            ;;
    esac
done
shift $(expr $OPTIND - 1)

if [ $# -eq 0 ]; then
    print_error "Missing volume name. $SEE_HELP_MSG"
    exit 1
fi

if [ $# -gt 1 ]; then
    print_error "Too many arguments. $SEE_HELP_MSG"
    exit 1
fi

VOLUME_NAME="$1"

VOLUME_DIR_PATH="$VOLUMES_DIR_PATH/$VOLUME_NAME"
FULL_COMMAND="minikube ssh 'sudo mkdir \"$VOLUME_DIR_PATH\" && sudo mount --bind \"$VOLUME_DIR_PATH\" \"$VOLUME_DIR_PATH\"'"

if [ "$DRY_RUN" -eq 1 ]; then
    echo "Dry-run. The command would be:"
    echo ""
    echo "$FULL_COMMAND"
else
    eval "$FULL_COMMAND"
fi
