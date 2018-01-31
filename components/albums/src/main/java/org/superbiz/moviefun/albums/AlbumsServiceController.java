package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumsServiceController
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AlbumRepository repository;

    public AlbumsServiceController(AlbumRepository repository)
    {
        this.repository = repository;
    }


    @GetMapping
    public Iterable<Album> getAll()
    {
        return repository.findAll();

    }

    @GetMapping("/{albumId}")
    public Album getAlbum(@PathVariable long albumId)
    {
        return repository.findOne(albumId);
    }

    @PostMapping
    public void newAlbum(@RequestBody Album album) {
        repository.save(album);
    }

    @PutMapping("/{albumId}")
    public void updateAlbum(@PathVariable long albumId, @RequestBody Album album) {
        repository.save(album);
    }


}
