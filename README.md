# Jargus metrics

Jargus metrics is fast, flexible and scalable monitoring solution with its own time series database.

## Features

tbd

## How to use

global: # Global settings
fetch: # Collect metrics from  target
interval: 1m # Schedule interval of collection
timeout: 10s # Maximum timeout of target

external_labels: # Labels to add to each metrics after collection from target
label_name1: labelValueA # Labels to add
label_name2: labelValueB # Labels to add

database: # Settings to database
clean_up_fixed_delay: 1m # Delay to clean metrics
metric_seconds_ttl: 30d # Time to live metrics with second interval
metric_minutes_ttl: 30d # Time to live metrics with minutes interval
metric_hours_ttl: 30d # Time to live metrics with hours interval

rules: # Rules to analyze metrics
- name: name # Name of rule
condition: condition # Condition to metric
labels:
- label_value1 # Label to add to alert message (do not link with labels of metrics)
- label_value2
annotation: annotation # Description add to messages

    - name: name
      condition: condition
      labels: label_name # Лейбл котороый крепится к сообщению от алерт системы (Не с)
      annotation: annotation

fetch_config: # Config to fetching targets
- task_name: name # Name of

  fetch:
  interval: 1m
  timeout: 10s

  metric_path: path
  target: uri

  request:
  headers:
  header1: valueA
  header2: valueB
  params:
  param_name1: param_valueA
  param_name2: param_valueB

  rules:
    - name: name
      condition: condition
      labels: label_name # Лейбл котороый крепится к сообщению от алерт системы
      annotation: annotation

    - name: name
      condition: condition
      labels: label_name # Лейбл котороый крепится к сообщению от алерт системы
      annotation: annotation

- task_name: name

  fetch:
  interval: 1m
  timeout: 10s

  metric_path: path
  target: uri

  request:
  headers:
  header1: valueA
  header2: valueB
  params:
  param_name1: param_valueA
  param_name2: param_valueB

  rules:
    - name: name
      condition: condition
      labels: label_name # Лейбл котороый крепится к сообщению от алерт системы
      annotation: annotation

    - name: name
      condition: condition
      labels: label_name # Лейбл котороый крепится к сообщению от алерт системы
      annotation: annotation


alerting:
host: uri
path: path
timeout: 10s



