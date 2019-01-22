docker build -t howdy-config-demo:0.1 .
docker run --rm -v $(pwd)/config:/config -p 8080:8080 howdy-config-demo:0.1

#### Commands to run on OpenShift:
```
oc new-build --binary --name=howdy-config-demo -l app=howdy-config-demo
oc start-build howdy-config-demo --from-dir=. --follow
oc new-app howdy-config-demo -l app=howdy-config-demo
oc expose svc/howdy-config-demo
oc set probe dc/howdy-config-demo --readiness --get-url=http://:8080/actuator/health
oc create configmap app-config --from-file=config/application.yaml


oc patch dc/howdy-config-demo -p '{
  "spec": {
    "template": {
      "spec": {
        "containers": [
          {
            "name": "howdy-config-demo",
            "volumeMounts": [
              {
                "name": "config-volume",
                "mountPath": "/config"
              }
            ]
          }
        ],
        "volumes": [
          {
            "name": "config-volume",
            "configMap": {
              "name": "app-config"
            }
          }
        ]
      }
    }
  }
}'

```