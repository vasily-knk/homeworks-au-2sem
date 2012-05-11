{-# LANGUAGE TupleSections #-}

import Map

sort :: Ord a => [a] -> [a]
sort = map fst . toList . fromList . map (, ())

main = print $ sort [10,24,13,56,35,13,6,23] -- [6,10,13,23,24,35,56]
