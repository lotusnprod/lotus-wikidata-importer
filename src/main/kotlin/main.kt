import org.apache.logging.log4j.LogManager
import org.wikidata.wdtk.datamodel.helpers.Datamodel
import org.wikidata.wdtk.wikibaseapi.BasicApiConnection
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher
import wd.WDCompoundSearch
import wd.WDPublisher
import wd.WDSparql


fun main(args: Array<String>) {
    val logger = LogManager.getLogger("main")
    logger.info("Playing with Wikidata Toolkit")

    /*logger.info("Initializing toolkit")
    val wikibaseDataFetcher = WikibaseDataFetcher(
        BasicApiConnection.getWikidataApiConnection(),
        Datamodel.SITE_WIKIDATA
    )*/

    /*logger.info("Fetching data for curcumin")
    val curcumin = wikibaseDataFetcher.getEntityDocument("Q312266")
    logger.info("Good for your curry!")*/

    /*logger.info("Initializing the Sparql stuff")
    val wdSparql = WDSparql()
    val wdCompound = WDCompoundSearch(wdSparql)*/

    val publisher = WDPublisher()
    publisher.connect()
    publisher.publish()
    publisher.disconnect()
}

