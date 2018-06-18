package com.devquartzo.stapigateway.model.routes;

import com.devquartzo.stcommon.model.Album;
import com.devquartzo.stcommon.model.Artist;
import com.devquartzo.stcommon.model.Song;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.remote.ConsulConfigurationDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteGateway extends RouteBuilder {
    @Autowired
    CamelContext context;

    @Override
    public void configure() throws Exception {
        ConsulConfigurationDefinition config = new ConsulConfigurationDefinition();
        config.setComponent("netty4-http");
        config.setUrl("http://192.168.0.14:8500");
        context.setServiceCallConfiguration(config);
        restConfiguration()
                .component("netty4-http")
                .bindingMode(RestBindingMode.json)
                .port(8000)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Example API").apiProperty("api.version", "1.0")
                .apiProperty("cors", "true");
        JacksonDataFormat formatSong = new JacksonDataFormat(Song.class);
        JacksonDataFormat formatArtist = new JacksonDataFormat(Artist.class);
        JacksonDataFormat formatAlbum = new JacksonDataFormat(Album.class);
        rest("/artists")
                .get("/{id}").description("Find Artist by id").outType(Artist.class)
                .param().name("id").type(RestParamType.path).description("Artist identificator").dataType("string").endParam()
                .route().serviceCall("st-artist").unmarshal(formatArtist)
                .endRest();
        rest("/songs")
                .get("/{id}").description("Find Song by id").outType(Song.class)
                .param().name("id").type(RestParamType.path).description("Song identificator").dataType("string").endParam()
                .route().serviceCall("st-song").unmarshal(formatSong)
                .endRest();
    }
}
