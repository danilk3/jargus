# Jargus metrics

Jargus metrics is fast, flexible and scalable monitoring solution with its own time series database.

## Configuration

```yaml
global_config:
  tsdb_config: 
    metric_seconds_ttl: <duration> | default = 30 days 
    metric_minutes_ttl:  <duration> | default = 14 days 
    metric_hours_ttl:  <duration> | default = 7 days
  fetch_config:
    interval: <duration> | default = 10 seconds
    timeout: <duration> | default = 10 seconds
  requests_config:
    headers:
      header_name1: header_value1
      header_name2: header_value2
    params:
      param_name1: param_value1
      param_name2: param_value2
  notification_rules_config:
    - name: <string>
      condition: <string>
      flags:
        - <string>
      annotation: <string>
    - ...

tasks_config:
  - task_name: <string>
    target: <string>
    path: <string>

alerting_config:
  host: <string>
  path: <string>
  timeout: <duration> | default = 10 seconds


```



