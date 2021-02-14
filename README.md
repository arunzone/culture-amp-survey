# culture-amp-survey

## Design thoughts

* Reading a survey from input source and display a report in console as output
* Application is composed of multiple layers
    * input - file
    * report - formatted output
    * output console
* To keep the design simple

## Assumptions

* input file name
    * single program argument - input survey question file name is sufficient to derive response file name by
      adding `-responses` suffix
    * two program arguments - first one for question and second one for response
* if the rating type is `singleselect` currently average operation is ignored as I'm not sure what is the criteria to
  calculate average on string

```
ex.
survey-1.csv
survey-1-response.csv
```

## Prerequisite

maven 3.6.3 Jdk 15

### Run test

> mvn clean verify

#### Coverage report

Code coverage report is available in
> target/site/jacoco/index.html

Report will be available after running test.

---

## Quality

### Findbugs

`mvn findbugs:gui`

### checkstyle

`mvn checkstyle::check`

### pmd

`mvn pmd::pmd`

---

### Practices

Had pre-commit git hook which run `mvn clean verify` before pushing so every push ensure better quality code

Used `jacoco` code coverage tool to fail build when code coverage is compromised
