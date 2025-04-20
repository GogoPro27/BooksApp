create materialized view books_per_author as
select a.id        as author_id,
       count(b.id) as num_books
from author a
         left join book b on b.author_id = a.id
group by a.id;

create materialized view authors_per_country as
select c.id        as country_id,
       count(a.id) as num_authors
from country c
         left join author a on a.country_id = c.id
group by c.id;