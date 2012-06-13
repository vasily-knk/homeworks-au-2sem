module Tree
    ( Tree(Leaf, Branch)
    , height
    , avg
    , width
    ) where

data Tree a b = Branch (Tree a b) a (Tree a b) | Leaf b

-- Возвращает высоту дерева
height :: Tree a b -> Int
height (Leaf _) = 0
height (Branch a _ b) = max (height a) (height b) + 1

-- Возвращает среднее арифметическое значений во всех узлах дерева
-- Необходимо вычислить эту функцию, выполнив один проход по дереву

avg :: Tree Int Int -> Int
avg t = dv $ calc t where
	dv (a, b) = div a b;
	calc (Leaf b) = (b, 1)
	calc (Branch l a r) = tpls (tpls (calc l) (calc r)) (a, 1) where
		tpls (x1, y1) (x2, y2) = (x1 + x2, y1 + y2)



	
-- Возвращает ширину дерева
-- Ширина дерева определяется следующим образом:
-- Количество вершин на определенном уровне называется шириной уровня.
-- Ширина дерева - это максимальная ширина уровня по всем уровням.
width :: Tree a b -> Int
width t = maximum $ level [] 0 t where
	updlst lst n 
		| length lst == n = lst ++ [1]
		| length lst > n = part1 ++ ((head part2 + 1) : tail part2)
		| otherwise = undefined where
			part1 = take n lst;
			part2 = drop n lst;
	level lst n (Leaf _) = updlst lst n
	level lst n (Branch a _ b) =  level (level (updlst lst n) (n + 1) a) (n + 1) b
				

tree = b (b (b l l) (b l (b l l))) (b l (b l (b l l)))
  where l = Leaf 500; b l r = Branch l 300 r

main = do
    print (height tree) -- 4
    print (avg tree) -- 405
    print (width tree) -- 6
