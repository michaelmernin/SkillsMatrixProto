# SkillsMatrixProto

Proof of concept skills matrix talent management system. 

Spring boot REST application, leveraging a graphDB, Neo4J. 

The rest service allows employees to manage their technical skillset, by creating relationships between
their accounts and technical skills/categories(eg: Skill: Spring Boot, Category: Java), through a web interface.

This is accomplished by leveraging Neo4j's ability to provide rich relationships between data.

Example Neo4j data structure representing an employees relationship to a skill, using Neo4J's query language syntax cypher:

(e:Employee {name: {employeeName}})-[r:HAS_SKILL]->(s:Skill {name: {skillName}})
