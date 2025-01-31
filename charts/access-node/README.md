# access-node

![Version: 0.8.1-PRE-65](https://img.shields.io/badge/Version-0.8.1--PRE--65-informational?style=flat-square) ![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square) ![AppVersion: 0.0.1](https://img.shields.io/badge/AppVersion-0.0.1-informational?style=flat-square)

Umbrella Chart for the DOME Marketplace access-node

## Requirements

| Repository | Name | Version |
|------------|------|---------|
| https://alastria.github.io/helm-charts | dlt-adapter | 8.0.0 |
| https://fiware.github.io/helm-charts | scorpio(scorpio-broker-aaio) | 0.4.9 |
| https://fiware.github.io/helm-charts | tm-forum-api | 0.10.11 |
| https://in2workspace.github.io/helm-charts | desmos | 1.0.4 |
| oci://registry-1.docker.io/bitnamicharts | kafka | 26.0.0 |
| oci://registry-1.docker.io/bitnamicharts | postgis(postgresql) | 13.1.5 |
| oci://registry-1.docker.io/bitnamicharts | postgresql(postgresql) | 13.1.5 |

## Values

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| desmos.app.dltAdapter.externalDomain | external | `"http://dlt-adapter:8080"` | address of the dlt-adapter |
| desmos.app.dltAdapter.internalDomain | local | `"http://dlt-adapter:8080"` | address of the dlt-adapter |
| desmos.app.dltAdapter.provider | string | `"digitelts"` | provider of the dlt-adapter component |
| desmos.app.externalAccessNodesUrls.customUrls | string | `"http://your-custom-url.org"` |  |
| desmos.app.externalAccessNodesUrls.enableCustomUrls | bool | `false` |  |
| desmos.app.externalDomain | string | `"<YOUR-EXTERNAL-DOMAIN>"` |  |
| desmos.app.internalServerPort | int | `8080` |  |
| desmos.app.logLevel.app | string | `"INFO"` |  |
| desmos.app.logLevel.root | string | `"INFO"` |  |
| desmos.app.ngsiSubscription.notificationEndpoint | string | `"http://desmos:8080/api/v1/notifications/broker"` | local address of the blockchain-connectors notification endpoint for ngsi-ld events |
| desmos.app.operator.externalDomain | string | `"http://scorpio:9090"` | external address of the broker. Will be included in the hashlink and used by other access-nodes to retrieve the actual entities |
| desmos.app.operator.internalDomain | string | `"http://scorpio:9090"` | internal address of the context broker to be used by the connector |
| desmos.app.operator.organizationIdentifier | string | `"<YOUR-ORGANIZATION-ID>"` | did of the organization running the node |
| desmos.app.operator.provider | string | `"scorpio"` | provider of the broker |
| desmos.app.privateKey | object | `{"existingSecret":{"enabled":false,"key":"<PRIVATE-KEY>","name":"<PRIVATE-KEY-SECRET>"},"value":"<YOUR-PRIVATE-KEY>"}` | configuration to set your private key |
| desmos.app.privateKey.existingSecret.enabled | bool | `false` | should an existing secret be used |
| desmos.app.privateKey.existingSecret.key | string | `"<PRIVATE-KEY>"` | key to retrieve the password from |
| desmos.app.privateKey.existingSecret.name | string | `"<PRIVATE-KEY-SECRET>"` | name of the secret |
| desmos.app.privateKey.value | string | `"<YOUR-PRIVATE-KEY>"` | your Ethereum private key |
| desmos.app.profile | string | `"dev"` |  |
| desmos.app.trustedAccessNodesList.customTrustedList | string | `"https://raw.githubusercontent.com/DOME-Marketplace/dome-access-node-directory/main/access-node-directory-"` |  |
| desmos.app.trustedAccessNodesList.enableCustomTrustedList | bool | `false` |  |
| desmos.app.txSubscription.notificationEndpoint | string | `"http://desmos:8080/api/v1/notifications/dlt"` | local address of the blockchain-connectors notification endpoint for dlt events |
| desmos.db.existingSecret.enabled | bool | `false` | should an existing secret be used |
| desmos.db.existingSecret.key | string | `"desmos-db-password"` | key to retrieve the password from |
| desmos.db.existingSecret.name | string | `"desmos-secret"` | name of the secret |
| desmos.db.externalService | bool | `false` | should be true if is an external service |
| desmos.db.host | string | `"postgresql-connector"` | host of the db |
| desmos.db.name | string | `"mktdb"` | name of the db |
| desmos.db.password | string | `"postgres"` | default password to be used |
| desmos.db.port | int | `5432` | port of the host of the db |
| desmos.db.username | string | `"postgres"` | username to be used |
| desmos.enabled | bool | `true` | should the desmos-blockchain-connector be enabled |
| desmos.fullnameOverride | string | `"desmos"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| desmos.image.pullPolicy | string | `"IfNotPresent"` |  |
| desmos.image.repository | string | `"in2workspace/in2-desmos-api"` |  |
| desmos.image.tag | string | `"v1.0.6"` |  |
| dlt-adapter.enabled | bool | `true` | should the dlt-adapter be enabled |
| dlt-adapter.env.DEBUG | string | `"*"` |  |
| dlt-adapter.env.ISS | string | `"<YOUR-ORGANIZATION-ID-IN-SHA256>"` |  |
| dlt-adapter.env.PRIVATE_KEY | string | `"<YOUR-PRIVATE-KEY>"` |  |
| dlt-adapter.env.RPC_ADDRESS | string | `"https://red-t.alastria.io/v0/9461d9f4292b41230527d57ee90652a6"` |  |
| dlt-adapter.existingSecret.enabled | bool | `false` |  |
| dlt-adapter.existingSecret.key | string | `"private-key"` |  |
| dlt-adapter.existingSecret.name | string | `"private-key-secret"` |  |
| dlt-adapter.fullnameOverride | string | `"dlt-adapter"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| dlt-adapter.service.port | int | `8080` |  |
| kafka.enabled | bool | `false` | should kafka be enabled? |
| postgis.auth.enablePostgresUser | bool | `true` | should the default postgres user be enabled |
| postgis.auth.password | string | `"postgres"` | username to be used |
| postgis.auth.username | string | `"postgres"` | username to be used |
| postgis.enabled | bool | `true` | should postgis be enabled |
| postgis.fullnameOverride | string | `"postgis"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| postgis.nameOverride | string | `"postgis"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| postgis.primary.initdb.scripts."enable.sh" | string | `"psql postgresql://postgres:${POSTGRES_PASSWORD}@localhost:5432 -c \"CREATE EXTENSION postgis;\"\npsql postgresql://postgres:${POSTGRES_PASSWORD}@localhost:5432 -c \"CREATE DATABASE ngb;\"\n"` | enable the postgis extension and create the database as expected by scorpio |
| postgresql.auth.enablePostgresUser | bool | `true` | should the default postgres user be enabled |
| postgresql.auth.password | string | `"postgres"` | password to be used |
| postgresql.auth.username | string | `"postgres"` | username to be used |
| postgresql.enabled | bool | `true` | should the postgresql deployment be enabled |
| postgresql.fullnameOverride | string | `"postgresql-connector"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| postgresql.nameOverride | string | `"postgresql-connector"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| postgresql.primary.initdb.scripts."create.sh" | string | `"psql postgresql://postgres:${POSTGRES_PASSWORD}@localhost:5432 -c \"CREATE DATABASE mktdb;\"\n"` | create the database as expected by the blockchain-connector |
| scorpio.db.dbhost | string | `"postgis"` | host of the db |
| scorpio.db.password | string | `"postgres"` | password to be used |
| scorpio.db.user | string | `"postgres"` | username to be used |
| scorpio.enabled | bool | `true` | should scorpio be enabled |
| scorpio.fullnameOverride | string | `"scorpio"` | overrides the generated name, provides stable service names - this should be avoided if multiple instances are available in the same namespace |
| scorpio.image.repository | string | `"scorpiobroker/all-in-one-runner"` | repository to be used - resource friendly all-in-one-runner without kafka |
| scorpio.image.tag | string | `"java-4.1.10"` | tag of the image to be used - latest java image without kafka |
| scorpio.livenessProbe.path | string | `"/q/health"` | path to be used for the readiness probe, older versions used /actuator/health |
| scorpio.readinessProbe.path | string | `"/q/health"` | path to be used for the readiness probe, older versions used /actuator/health |
| scorpio.service.type | string | `"ClusterIP"` | ClusterIP is the recommended type for most clusters |
| tm-forum-api.apiProxy.enabled | bool | `true` | should the proxy be enabled |
| tm-forum-api.defaultConfig.contextUrl | string | `"https://uri.etsi.org/ngsi-ld/v1/ngsi-ld-core-context.jsonld"` | default context to be used when contacting the context broker |
| tm-forum-api.defaultConfig.ngsiLd | object | `{"url":"http://scorpio:9090"}` | ngsi-ld broker connection information |
| tm-forum-api.defaultConfig.ngsiLd.url | string | `"http://scorpio:9090"` | address of the broker |
| tm-forum-api.defaultConfig.serverHost | string | `"http://localhost:8080"` | host that the tm-forum api can be reached at, when the proxy is enabled it should be set to that address. If not, set the host for each api individually |
| tm-forum-api.enabled | bool | `true` | should tm-forum-api be enabled |

----------------------------------------------
Autogenerated from chart metadata using [helm-docs v1.14.2](https://github.com/norwoodj/helm-docs/releases/v1.14.2)
