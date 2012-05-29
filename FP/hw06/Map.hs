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

data Map k v = MapTree (Tree (k, v) (k, v)) | EmptyMap

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
lookup k' EmptyMap = Nothing
lookup k' MapTree t = lookupTree t where
	lookupTree
	MapTree (Leaf (k, v)) =


		
		-- Возвращает обновленное дерево и Nothing, если до этого в дереве не было ключа k, и Just v, если в дереве по ключу k было значение v
insert :: Ord k => k -> v -> Map k v -> (Map k v, Maybe v)
insert = undefined

-- Возвращает Nothing, если ключа k нет в дереве и Just t, если есть, где t - дерево с удаленным ключом k
delete :: Ord k => k -> Map k v -> Maybe (Map k v)
delete = undefined

fromList :: Ord k => [(k, v)] -> Map k v
fromList [] = EmptyMap 
fromList lst =  helper EmptyMap lst where
	helper m lst 
		| lst == [] = m
		| otherwise = helper (insertpair (head lst) m) where
			insertpair (k, v) m = fst $ insert k v m
	--inserter (k, v) = insert k 

-- Обход в инфиксном порядке
toList :: Map k v -> [(k, v)]
toList = undefined
