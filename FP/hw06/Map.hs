module Map
    ( Map
    , lookup
    , insert
    , delete
    , fromList
    , toList
    ) where

import Prelude hiding (lookup)

data Map k v = ...

lookup :: Ord k => k -> Map k v -> Maybe v
lookup = undefined

-- Возвращает обновленное дерево и Nothing, если до этого в дереве не было ключа k, и Just v, если в дереве по ключу k было значение v
insert :: Ord k => k -> v -> Map k v -> (Map k v, Maybe v)
insert = undefined

-- Возвращает Nothing, если ключа k нет в дереве и Just t, если есть, где t - дерево с удаленным ключом k
delete :: Ord k => k -> Map k v -> Maybe (Map k v)
delete = undefined

fromList :: Ord k => [(k, v)] -> Map k v
fromList = undefinde

-- Обход в инфиксном порядке
toList :: Map k v -> [(k, v)]
toList = undefined
