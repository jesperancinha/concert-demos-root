package org.jesperancinha.concerts.mvc.model

import javax.persistence.*

@Entity
data class Concert(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    val name: String,
    val location: String,
    val date: String,
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "concert_listing",
        joinColumns = [JoinColumn(name = "listing_id")],
        inverseJoinColumns = [JoinColumn(name = "concert_id")]
    )
    var listings: MutableSet<Listing>? = HashSet(),
)