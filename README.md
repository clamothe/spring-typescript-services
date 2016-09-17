[![Stories in Ready](https://badge.waffle.io/mkowalzik/spring-typescript-services.png?label=ready&title=Ready)](https://waffle.io/mkowalzik/spring-typescript-services)
[![Build Status][travisbadge img]][travisbadge]
[![Coverity Scan Build Status][coveritybadge img]][coveritybadge]
[![codecov][codecov img]][codecov]
[![Passing Tests][sonar tests img]][sonar tests]
[![Quality Gate][sonar quality img]][sonar quality]
[![Tech Debt][sonar tech img]][sonar tech]
[![Maven Status][mavenbadge img]][mavenbadge]
[![Dependencies status][versioneye img]][versioneye]
[![license][license img]][license]

# spring-typescript-services
Generate typescript services and type interfaces from spring annotated @RestControllers.

Get strongly typed interfaces for your spring-boot microservices in no time.

# What is it?
A Java annotation processor to generate a service and TypeScript types to access your spring @RestControllers.

# How does it work?
It generates a service.ts file for every with @TypeScriptEndpoint annotated class and includes functions 
for every enclosed public Java Method with a @RequestMapping producing JSON.
The TypeScript files are generated by populating [<#FREEMARKER>][freemarker]-template files. 
You can specify your own template files or use the bundled defaults.

# Getting started
Just specify the dependency in your maven based build.

```xml
<dependency>
    <groupId>org.leandreck.endpoints</groupId>
    <artifactId>annotations</artifactId>
    <scope>provided</scope> <!-- the annotations and processor are only needed at compile time -->
    <optional>true</optional> <!-- they need not to be transitively included in dependent artifacts -->
</dependency>
<!-- * because of spring-boot dependency handling they nevertheless get included in fat jars -->
```

# Example
The following snippet will produce a TestTypeScriptEndpoint.ts and a RootType.model.ts file.
```java
import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
//...
import static org.springframework.web.bind.annotation.RequestMethod.*;

@TypeScriptEndpoint
@RestController
@RequestMapping("/api")
public class TestTypeScriptEndpoint {

    @RequestMapping(value = "/persons", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<RootType> getPersons() {
        final List<RootType> rootTypes = new ArrayList<>();
        rootTypes.add(new RootType());
        return rootTypes;
    }
```
and the produced TypeScript files from the default templates look like:

```typescript
import { IRootType } from './IRootType.model';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

@Injectable()
export class TestTypeScriptEndpoint {

    private serviceBaseURL = '/api'
    
    constructor(private _http: Http) { }

    get_getPersons(): IRootType[] {
    return this._http.get(this.serviceBaseURL + '/persons')
        .map((res: Response) => res.json())
        .catch(this.handleError);
    }
```

[freemarker]: http://freemarker.org/

[travisbadge]:https://travis-ci.org/mkowalzik/spring-typescript-services
[travisbadge img]:https://travis-ci.org/mkowalzik/spring-typescript-services.svg?branch=master

[coveritybadge]:https://scan.coverity.com/projects/mkowalzik-spring-typescript-services
[coveritybadge img]:https://scan.coverity.com/projects/10040/badge.svg

[sonar quality]:https://sonarqube.com/overview?id=org.leandreck.endpoints%3Aparent
[sonar quality img]:https://sonarqube.com/api/badges/gate?key=org.leandreck.endpoints:parent

[sonar tech]:https://sonarqube.com/overview?id=org.leandreck.endpoints%3Aparent
[sonar tech img]:https://img.shields.io/sonar/http/sonarqube.com/org.leandreck.endpoints:parent/tech_debt.svg?label=tech%20debt

[sonar tests]:https://sonarqube.com/component_measures/metric/tests/list?id=org.leandreck.endpoints%3Aparent
[sonar tests img]:https://img.shields.io/sonar/http/sonarqube.com/org.leandreck.endpoints:parent/test_success_density.svg?label=passing%20tests%20%

[mavenbadge]:http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.leandreck.endpoints%22%20AND%20a%3A%22annotations%22
[mavenbadge img]:https://maven-badges.herokuapp.com/maven-central/org.leandreck.endpoints/annotations/badge.svg

[versioneye]:https://www.versioneye.com/user/projects/57c73c6986473900166cd1a2
[versioneye img]:https://www.versioneye.com/user/projects/57c73c6986473900166cd1a2/badge.svg

[license]:LICENSE
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg

[codecov]:https://codecov.io/gh/mkowalzik/spring-typescript-services
[codecov img]:https://codecov.io/gh/mkowalzik/spring-typescript-services/branch/master/graph/badge.svg
