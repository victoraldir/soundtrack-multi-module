package com.devquartzo.stapigateway.model.routes;


import com.devquartzo.stcommon.album.model.Album;
import com.devquartzo.stcommon.artist.model.Artist;
import com.devquartzo.stcommon.song.model.Song;
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
        config.setUrl("http://localhost:8500");
        context.setServiceCallConfiguration(config);
        restConfiguration()
                .component("netty4-http")
                .bindingMode(RestBindingMode.json)
                .port(8000)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Sound Tracker API").apiProperty("api.version", "1.0")
                .apiProperty("cors", "true");

        JacksonDataFormat formatSong = new JacksonDataFormat(Song.class);
        JacksonDataFormat formatArtist = new JacksonDataFormat(Artist.class);
        JacksonDataFormat formatArtistList = new JacksonDataFormat(Artist.class);
        formatArtistList.useList();
        JacksonDataFormat formatAlbum = new JacksonDataFormat(Album.class);

        rest("/artists")
                .get("/{id}").description("Find Artist by id").outType(Artist.class)
                .param().name("id").type(RestParamType.path).description("Artist identificator").dataType("string").endParam()
                .route().serviceCall("st-artist").log("Service Call Reponse is: ${body}").unmarshal(formatArtist)
                .endRest()

                .get("/").description("List Artists").outType(Artist.class)
                .route().serviceCall("st-artist").unmarshal(formatArtistList)
                .endRest()

                .post("/post").description("Insert Artist").outType(Artist.class)
                .route().log("Body id: ${body}").serviceCall("st-artist").log("Body after call is: ${body}")
                .endRest();

        rest("/songs")
                .get("/{id}").description("Find Song by id").outType(Song.class)
                .param().name("id").type(RestParamType.path).description("Song identificator").dataType("string").endParam()
                .route().serviceCall("st-song").unmarshal(formatSong)
                .endRest();
    }
}
