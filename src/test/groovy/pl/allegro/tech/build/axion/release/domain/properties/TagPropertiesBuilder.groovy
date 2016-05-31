package pl.allegro.tech.build.axion.release.domain.properties

import pl.allegro.tech.build.axion.release.domain.TagNameSerializer

class TagPropertiesBuilder {

    Closure branchName = { TagProperties rules, String version -> 'rel-0.1.0' }

    private TagPropertiesBuilder() {
    }

    static TagPropertiesBuilder tagProperties() {
        return new TagPropertiesBuilder()
    }

    TagProperties build() {
        return new TagProperties(
                serialize: TagNameSerializer.DEFAULT.serializer,
                deserialize: TagNameSerializer.DEFAULT.deserializer,
                prefix: 'release',
                versionSeparator: '-',
                branchName: branchName,
                initialVersion: { r, p -> '0.1.0' }
        )
    }

    TagPropertiesBuilder withBranchName(Closure closure) {
        this.branchName = closure
        this
    }

}
