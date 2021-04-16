#!/usr/bin/env sh

#
# Verifies that all the required input variables have been set
#

if [ -z "$CURRENT_NODE_HOSTNAME" ]; then
	echo "Missing CURRENT_NODE_HOSTNAME"
	exit 1
fi
if [ -z "$SEEDER_NODE_HOSTNAME" ]; then
	echo "Missing SEEDER_NODE_HOSTNAME"
	exit 1
fi
if [ -z "$SERVICE_NAME" ]; then
	echo "Missing SERVICE_NAME"
	exit 1
fi

echo "CURRENT_NODE_HOSTNAME: $CURRENT_NODE_HOSTNAME"
echo "SEEDER_NODE_HOSTNAME: $SEEDER_NODE_HOSTNAME"
echo "SERVICE_NAME: $SERVICE_NAME"

#
# Determines which seed to use to bootstrap this node
#

if [ "$CURRENT_NODE_HOSTNAME" = "$SEEDER_NODE_HOSTNAME" ]; then
	echo "This is the cluster's seeder node"
	OTHER_NODES=`getent hosts tasks.$SERVICE_NAME`
	OTHER_NODES_COUNT=`printf '%s' "$OTHER_NODES" | wc -l`
	if [ "$OTHER_NODES_COUNT" -eq "0" ]; then
		echo "No other node has bootstrapped yet"
		export CASSANDRA_SEEDS=`hostname`
	else
		echo "Found $OTHER_NODES_COUNT nodes already bootstrapped:"
		printf '%s' "$OTHER_NODES"
		export CASSANDRA_SEEDS=$SERVICE_NAME
	fi
else
	echo "This is a leecher node"
	export CASSANDRA_SEEDS=$SERVICE_NAME
fi

#
# Bootstraps this node
#

/docker-entrypoint.sh

