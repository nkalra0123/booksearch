# booksearch

Build a search engine to search gourmet food reviews data and return the top K
reviews that have the highest overlap with the input query.

Dataset available at : https://snap.stanford.edu/data/web-FineFoods.html

Queries are input as a set of tokens (words), Ties between scores can be
resolved using review/score for the document.

Score(D, Q) = Q ⋂ D
(i.e. # tokens matching between Query(Q) & Document(D) normalized by query
length the number of tokens in the given query).
