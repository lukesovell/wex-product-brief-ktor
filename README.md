# wex-product-brief

Sample Product Brief to get some practice to Kotlin, Ktor, and Exposed

TODO: 

* Add tests
* Database migration using Flyway

JOOQ Thoughts:
It has it's advantages. Once the plugin was configured correctly it was easy to start writing type-safe queries 
since the table definition was generated from my migration file. But if I had a lot of migrations and tables
I worry it would slow the build down significantly. I also had trouble getting the plugin configured correctly in 
the first place. I don't love the DSL syntax, it seems like it could get messy, but at least it is pretty close to SQL. 