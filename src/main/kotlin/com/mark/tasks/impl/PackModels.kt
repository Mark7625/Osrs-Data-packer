package com.mark.tasks.impl

import com.displee.cache.CacheLibrary
import com.mark.tasks.CacheTask
import com.mark.util.decompressGzipToBytes
import com.mark.util.getFiles
import com.mark.util.progress
import java.io.File
import java.nio.file.Files

/*
 * Packs Models into the osrs cache
 */
class PackModels(private val modelDirectory : File) : CacheTask() {
    override fun init(library: CacheLibrary) {
        val modelSize = getFiles(modelDirectory,"gz","dat").size
        val progressModels = progress("Packing Models", modelSize)
        if (modelSize != 0) {
            getFiles(modelDirectory,"gz","dat").forEach {
                val id = it.nameWithoutExtension.toInt()
                val buffer = if (it.extension == "gz") decompressGzipToBytes(it.toPath()) else Files.readAllBytes(it.toPath())
                library.put(7, id, buffer)
                progressModels.step()
            }
            progressModels.close()
        }
    }

}