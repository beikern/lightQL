## Quick start

lightQL is published to [Bintray jcenter][bintray-jcenter] and synchronized
to [Maven Central][maven-central] (albeit the latter is not an exact science). The library is
cross-built both for Scala 2.11 and 2.12.


In order to work with the Elasticsearch materializer (the only available for the time being),
just include the proper SBT configuration[^1sbt]:

```scala
libraryDependencies += "com.tecsisa" %% "lightql-elastic" % "5.3.0"
```

Then, open a SBT REPL session and try this sequence of commands:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._
                                         ^
<console>:12: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._
                                                                            ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic._

scala> val qs = "foo = 100"
<console>:10: warning: Unused import
import com.tecsisa.lightql.parser._
                                  ^
<console>:13: warning: Unused import
import com.tecsisa.lightql.mat.elastic._
                                       ^
qs: String = foo = 100

scala> materialize(parse(qs).get.value)
res0: com.sksamuel.elastic4s.searches.queries.QueryDefinition = TermQueryDefinition(foo,100,None,None)
```

On the other hand, if you're a happy [elastic4s][elastic4s-github-url] user (and sure you are) you'll be able to take advantage
of the seamless integration between these two libraries and declare search expressions easily:

```scala
scala> import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._, com.sksamuel.elastic4s.ElasticDsl._
<console>:10: warning: Unused import
import com.tecsisa.lightql.parser._
                                  ^
<console>:13: warning: Unused import
import com.tecsisa.lightql.mat.elastic._
                                       ^
<console>:18: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._, com.sksamuel.elastic4s.ElasticDsl._
                                         ^
<console>:18: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._, com.sksamuel.elastic4s.ElasticDsl._
                                                                            ^
<console>:18: warning: Unused import
       import com.tecsisa.lightql.parser._, com.tecsisa.lightql.mat.elastic._, com.sksamuel.elastic4s.ElasticDsl._
                                                                                                                 ^
import com.tecsisa.lightql.parser._
import com.tecsisa.lightql.mat.elastic._
import com.sksamuel.elastic4s.ElasticDsl._

scala> def q(qs: String) = parse(qs).get.value
<console>:10: warning: Unused import
import com.tecsisa.lightql.parser._
                                  ^
<console>:13: warning: Unused import
import com.tecsisa.lightql.mat.elastic._
                                       ^
<console>:15: warning: Unused import
import qs
                                            ^
<console>:20: warning: Unused import
import com.tecsisa.lightql.mat.elastic._
                                       ^
<console>:23: warning: Unused import
import com.sksamuel.elastic4s.ElasticDsl._
                                         ^
q: (qs: String)com.tecsisa.lightql.ast.Query

scala> search("songs") query q("composer = \"Johann Sebastian Bach\"")
<console>:10: warning: Unused import
import com.tecsisa.lightql.parser._
                                  ^
<console>:13: warning: Unused import
import com.tecsisa.lightql.mat.elastic._
                                       ^
<console>:16: warning: Unused import
import com.tecsisa.lightql.parser._
                                  ^
res1: com.sksamuel.elastic4s.searches.SearchDefinition = SearchDefinition(IndexesAndTypes(WrappedArray(songs),List()),List(),None,None,None,None,List(),List(),None,None,None,None,Some(TermQueryDefinition(composer,Johann Sebastian Bach,None,None)),None,None,List(),List(),List(),List(),List(),None,None,List(),None,List(),None,None,None,None)
```

[^1sbt]: Please, use the 2.3.x installments in case you're working with Elasticsearch 2.3.x.  Please, raise a ticket in case you need a 2.4.x compatible release.
