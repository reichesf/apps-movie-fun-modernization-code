package org.superbiz.moviefun.albumsapi; /**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class AlbumsClient
{
    private String url;
    private RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations)
    {
        this.url = albumsUrl;
        this.restOperations = restOperations;
    }

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>()
    {
    };

    public void addAlbum(AlbumInfo album)
    {
        restOperations.postForEntity(url, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id)
    {
        return restOperations.exchange(url + "/" + id, GET, null, AlbumInfo.class).getBody();
    }

    public List<AlbumInfo> getAlbums()
    {
        return restOperations.exchange(url, GET, null, albumListType).getBody();
    }


    public void deleteAlbum(AlbumInfo album)
    {
        restOperations.delete(url + "/" + album.getId());
    }


    public void updateAlbum(AlbumInfo album)
    {
        restOperations.put(url + "/" + album.getId(), album);
    }

}
