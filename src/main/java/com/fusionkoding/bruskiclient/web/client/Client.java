package com.fusionkoding.bruskiclient.web.client;

import java.net.URI;

public interface Client<O, I> {
    public O getById(I id);

    public O getByUri(URI uri);

    public URI save(O dto);

    public void update(I id, O dto);

    public void delete(I id);
}
