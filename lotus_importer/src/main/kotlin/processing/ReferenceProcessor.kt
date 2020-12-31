/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 *
 * Copyright (c) 2020 Jonathan Bisson
 *
 */

package processing

import input.DataTotal
import input.Reference
import net.nprod.lotus.wdimport.wd.InstanceItems
import net.nprod.lotus.wdimport.wd.WDFinder
import net.nprod.lotus.wdimport.wd.models.entries.WDArticle
import net.nprod.lotus.wdimport.wd.publishing.IPublisher
import org.apache.logging.log4j.LogManager

class ReferenceProcessor(
    val dataTotal: DataTotal,
    val publisher: IPublisher,
    val wdFinder: WDFinder,
    val instanceItems: InstanceItems
) {
    private val logger = LogManager.getLogger(ReferenceProcessor::class.qualifiedName)
    private val articlesCache: MutableMap<Reference, WDArticle> = mutableMapOf()

    private fun articleFromReference(reference: Reference): WDArticle {
        val article = WDArticle(
            label = reference.title ?: reference.doi,
            title = reference.title,
            doi = reference.doi.toUpperCase(), // DOIs are always uppercase
        ).tryToFind(wdFinder, instanceItems)

        publisher.publish(article, "upserting article")
        return article
    }

    fun get(key: Reference): WDArticle {
        return articlesCache.getOrPut(key) {
            articleFromReference(key)
        }
    }
}