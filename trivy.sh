#!/usr/bin/env sh
docker run \
    --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v $HOME/.cache/trivy:/root/.cache/ \
    aquasec/trivy \
    -f json \
    --quiet \
    $1 \
    > build/trivy_results.json
