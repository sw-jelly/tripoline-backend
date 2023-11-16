insert into tripoline.attraction_info (content_id, content_type_id, title, addr1, addr2, zipcode, tel, first_image ,first_image2 ,sido_code, gugun_code, latitude, longitude, overview)
(
    select a.content_id, a.content_type_id, a.title, a.addr1, a.addr2, a.zipcode, a.tel, a.first_image, a.first_image2, a.sido_code, a.gugun_code, a.latitude, a.longitude, b.overview
    from enjoytrip.attraction_info a left join enjoytrip.attraction_description b
    on a.content_id = b.content_id
);

insert into tripoline.sido (sido_code, sido_name)
(
    select sido_code, sido_name
    from enjoytrip.sido
);

insert into tripoline.gugun (gugun_code, gugun_name, sido_code)
(
    select gugun_code, gugun_name, sido_code
    from enjoytrip.gugun
);
insert into tripoline.gugun (gugun_code, gugun_name, sido_code)
(
    select gugun_code, gugun_name, sido_code
    from enjoytrip.gugun
);