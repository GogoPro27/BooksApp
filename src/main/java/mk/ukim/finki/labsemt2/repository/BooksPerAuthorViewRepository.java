package mk.ukim.finki.labsemt2.repository;

import mk.ukim.finki.labsemt2.model.views.BooksPerAuthorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.books_per_author", nativeQuery = true)
    void refreshMaterializedView();
}
