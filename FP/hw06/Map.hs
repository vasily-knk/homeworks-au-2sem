module Map
    ( Map
    , lookup
    , insert
    , delete
    , fromList
    , toList
    ) where

import Prelude hiding (lookup)
import Tree

type Map k v = Tree (k, v) ()

-- lookup :: Ord k => k -> Map k v -> Maybe v
-- lookup k' EmptyMap = Nothing
-- lookup k' (MapTree (Leaf (k, v))) 
-- 	| k' == k = Just v 
-- 	| otherwise = Nothing
-- lookup k' (MapTree (Branch l (k, v) r)) 
-- 	| k' == k = Just v
-- 	| otherwise = chooseone (lookup l) (lookup r) where
-- 		chooseone (Just v) _ = v
-- 		chooseone _ r = r

lookup :: Ord k => k -> Map k v -> Maybe v
lookup k t = lookupTree k t where 
    lookupTree k (Leaf ()) = Nothing
    lookupTree k (Branch l (k', v) r)
        | k == k'   = Just v
        | k < k'    = lookupTree k l
        | otherwise = lookupTree k r

-- Возвращает обновленное дерево и Nothing, если до этого в дереве не было ключа k, и Just v, если в дереве по ключу k было значение v
insert :: Ord k => k -> v -> Map k v -> (Map k v, Maybe v)
insert k v (Leaf()) = (Branch (Leaf()) (k, v) (Leaf()), Nothing)
insert k v (Branch l (k', v') r) 
    | k == k'   = (Branch l (k', v') r, Just v')
    | k < k'    = hangLeft  (insert k v l) (k', v') r 
    | otherwise = hangRight (insert k v r) l (k', v') where 
        hangLeft  (l, res) (k', v') r = (Branch l (k', v') r, res);
        hangRight (r, res) l (k', v') = (Branch l (k', v') r, res)
    
    
insertSilent :: Ord k => k -> v -> Map k v -> Map k v
insertSilent k v = fst . insert k v

insertPair :: Ord k => Map k v -> (k, v) -> Map k v
insertPair m (k, v) = insertSilent k v m

--getMin :: Ord k => Map k v -> Maybe (k, v)
--getMin (Leaf()) = Nothing
--getMin (Branch l (k, v) r) = Just $ helper (getMin l, (k, v)) where
    --helper :: (Maybe (k, v), (k, v)) -> (k, v)
    --helper (Nothing, x) = x
    --helper ((Just x), _) = x
    

-- Возвращает Nothing, если ключа k нет в дереве и Just t, если есть, где t - дерево с удаленным ключом k
delete :: Ord k => k -> Map k v -> Maybe (Map k v)
delete _ (Leaf()) = Nothing
delete k (Branch l (k', v') r) 
    | k < k' = hangLeft  (delete k l) (k', v') r
    | k > k' = hangRight l (k', v') (delete k r)
    | otherwise = deleteRoot l (k', v') r where
        hangLeft Nothing _ _  = Nothing
        hangLeft (Just l) (k', v') r  = Just $ Branch l (k', v') r;
        
        hangRight _ _ Nothing = Nothing
        hangRight l (k', v') (Just r) = Just $ Branch l (k', v') r;
        
        getMin (Branch (Leaf()) (k, v) _) = (k, v)
        getMin (Branch l _ _) = getMin l;
        
        deleteRoot l _ (Leaf()) = Just l
        deleteRoot (Leaf()) _ r = Just r
        deleteRoot l _ r = hangRight l (getMin r) (delete (fst $ getMin r) r)
        
    

fromList :: Ord k => [(k, v)] -> Map k v
fromList list = foldl insertPair (Leaf()) list

-- Обход в инфиксном порядке
toList :: Ord k => Map k v -> [(k, v)]
toList (Leaf()) = []
toList (Branch l (k, v) r) = toList l ++ (k, v) : toList r
