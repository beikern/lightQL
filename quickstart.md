## Quick start

lightQL is published to [Bintray jcenter][bintray-jcenter] and synchronized
to [Maven Central][maven-central] (albeit the latter is not an exact science). The library is
cross-built both for Scala 2.11 and 2.12.


In order to work with either the HTTP or TCP Elasticsearch materializer (the only ones available for the time being),
just include the proper SBT configuration[^1sbt]:

```scala
libraryDependencies += "com.tecsisa" %% "lightql-elastic-http" % "5.6.1"
```
or
```scala
libraryDependencies += "com.tecsisa" %% "lightql-elastic-tcp" % "5.6.1"
```

Then, open a SBT REPL session and try this sequence of commands:

HTTP:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._
                                         ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._
                                                                                 ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic.http._

scala> val qs = "foo = 100"
<console>:10: warning: Unused import
       import com.tecsisa.lightql.parser._
                                         ^
<console>:13: warning: Unused import
       import com.tecsisa.lightql.mat.elastic.http._
                                                   ^
qs: String = foo = 100

scala> materialize(parse(qs).get.value)
res0: com.sksamuel.elastic4s.searches.queries.QueryDefinition = TermQueryDefinition(foo,100,None,None)
```

TCP:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._
                                         ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._
                                                                                ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic.tcp._

scala> val qs = "foo = 100"
<console>:10: warning: Unused import
       import com.tecsisa.lightql.parser._
                                         ^
<console>:13: warning: Unused import
       import com.tecsisa.lightql.mat.elastic.tcp._
                                                  ^
qs: String = foo = 100

scala> materialize(parse(qs).get.value)
res0: com.sksamuel.elastic4s.searches.queries.QueryDefinition = TermQueryDefinition(foo,100,None,None)
```

On the other hand, if you're a happy [elastic4s][elastic4s-github-url] user (and sure you are) you'll be able to take advantage
of the seamless integration between these two libraries and declare search expressions easily:

HTTP:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._, com.sksamuel.elastic4s.http.ElasticDsl._
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._, com.sksamuel.elastic4s.http.ElasticDsl._
                                         ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._, com.sksamuel.elastic4s.http.ElasticDsl._
                                                                                 ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.http._, com.sksamuel.elastic4s.http.ElasticDsl._
                                                                                                                           ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic.http._
import com.sksamuel.elastic4s.http.ElasticDsl._

scala> def q(qs: String) = parse(qs).get.value
<console>:13: warning: Unused import
       import com.tecsisa.lightql.mat.elastic.http._
                                                   ^
<console>:16: warning: Unused import
       import com.sksamuel.elastic4s.http.ElasticDsl._
                                                     ^
q: (qs: String)com.tecsisa.lightql.ast.Query

scala> search("songs") query q("composer = \"Johann Sebastian Bach\"")
<console>:10: warning: Unused import
       import com.tecsisa.lightql.parser._
                                         ^
res0: com.sksamuel.elastic4s.searches.SearchDefinition = SearchDefinition(IndexesAndTypes(WrappedArray(songs),List()),List(),None,None,None,None,None,List(),List(),None,None,None,None,Some(TermQueryDefinition(composer,Johann Sebastian Bach,None,None)),None,None,List(),List(),List(),List(),List(),None,None,None,List(),None,List(),None,None,None,None)
```

TCP:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._, com.sksamuel.elastic4s.ElasticDsl._
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._, com.sksamuel.elastic4s.ElasticDsl._
                                         ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._, com.sksamuel.elastic4s.ElasticDsl._
                                                                                ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic.tcp._, com.sksamuel.elastic4s.ElasticDsl._
                                                                                                                     ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic.tcp._
import com.sksamuel.elastic4s.ElasticDsl._

scala> def q(qs: String) = parse(qs).get.value
<console>:13: warning: Unused import
       import com.tecsisa.lightql.mat.elastic.tcp._
                                                  ^
<console>:16: warning: Unused import
       import com.sksamuel.elastic4s.ElasticDsl._
                                                ^
q: (qs: String)com.tecsisa.lightql.ast.Query

scala> search("songs") query q("composer = \"Johann Sebastian Bach\"")
<console>:10: warning: Unused import
       import com.tecsisa.lightql.parser._
                                         ^
res0: com.sksamuel.elastic4s.searches.SearchDefinition = SearchDefinition(IndexesAndTypes(WrappedArray(songs),List()),List(),None,None,None,None,None,List(),List(),None,None,None,None,Some(TermQueryDefinition(composer,Johann Sebastian Bach,None,None)),None,None,List(),List(),List(),List(),List(),None,None,None,List(),None,List(),None,None,None,None)
```

[^1sbt]: Please, use the 2.3.x installments in case you're working with Elasticsearch 2.3.x.  Please, raise a ticket in case you need a 2.4.x compatible release.
