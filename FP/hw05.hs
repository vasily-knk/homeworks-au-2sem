module Hw05 where

-- 1. Дан список. Написать функцию, возвращающую список пар: элемент и его номер, т.е. f "abc" == [('a',0),('b',1),('c',2)]

-- a) При помощи рекурсии.
numlist :: [a] -> [(a, Int)]
numlist l = helper [] l where
	helper l [] = l
	helper [] (x:r) = helper [(x, 0)] r
	helper l (x:r) = helper (l ++ [(x, (snd.last) l + 1)]) r

-- b) При помощи функции zip.
numlist' :: [a] -> [(a, Int)]
numlist' l = zip l [0..]



-- 2. Написать функцию fun :: [Int] -> [Int], которая четные числа в нечетных позициях (нумеруя с 0) умножает на 2, остальные не изменяет

fun :: [Int] -> [Int]
fun l = map transform (numlist l) where
	transform a = if ((even.fst) a) && ((odd.snd) a)
		then (fst a) * 2
		else fst a

-- 3. Реализовать reverse.
-- a) При помощи (++)

reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:l) = (reverse l) ++ [x]

-- b) При помощи аккумулирующего параметра.

reverse'' :: [a] -> [a]
reverse'' l = helper [] l where
	helper l [] = l
	helper l (x:r) = helper ([x] ++ l) r

-- Сравнить их время работы на списке [1..10000]

--4. Реализовать следующие функции, используя композицию:
-- a) nelem :: a -> [a] -> Bool, которая работает как функция notElem. Используйте функцию elem.

nelem :: Eq a => a -> [a] -> Bool
nelem a = not.elem a

-- b) f :: (Int -> Int) -> Int -> Bool. f g x должен возвращать True, если g x четен. Используйте функцию even.
f :: (Int -> Int) -> Int -> Bool
f g = even.g

-- c) f :: [Int] -> Bool. f xs возвращает True, если в xs есть хотя бы 1 положительное число, иначе False. Используйте функции filter и null.

f' :: [Int] -> Bool
f' = not.null.filter (> 0)

-- d) f :: (a -> Bool) -> [a] -> Int. f p xs возвращает количество элементов в xs, не удовлетворяющих предикату p. Используйте функции filter и length.

f'' :: (a -> Bool) -> [a] -> Int
f'' p  = length.filter p

-- e) f :: [Int] -> Int. f возвращает сумму первых 10 элементов списка.

f''' :: [Int] -> Int
f''' = sum.take 10

-- f) f :: [Int] -> Int. f каждый элемент умножает на 2, потом прибавляет 3 и возвращает произведение всех элементов. f [1,2,3] == 315. Используйте функцию product.

f'''' :: [Int] -> Int
f'''' = product.map ((+ 3).(* 2))

-- 5. primes :: [Integer] -- бесконечный список простых чисел. Hint: напишите сначала функцию isPrime, проверяющую простоту числа.

isPrime :: Int -> Bool
isPrime n = null $ filter check [2..ceiling $ sqrt $ fromIntegral n] where
	check m = mod n m == 0

primes :: [Int]
primes = filter isPrime [1..]

-- 6. fibs :: [Integer] -- бесконечный список чисел фибоначчи.
fibs :: [Integer]
fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

 
-- 7. swap :: Int -> Int -> [a] -> [a] -- swap i j меняет местами i и j элементы. Например swap 1 2 [3,4,5,6] == [3,5,4,6]. swap 2 0 "abcd" == "cbad". 
swap :: Int -> Int -> [a] -> [a]
swap i j lst 
	| i == j = lst
	| i > j = swap j i lst
	| otherwise = part1 ++ [head part3] ++ tail part2 ++ [head part2] ++ tail part3 where
		part1 = take i lst;
		part2 = take (j - i) $ drop i lst;
		part3 = drop j lst
 
-- 8. Задана рекурентная последовательность a(0) = 1, a(1) = 2, a(2) = 3, a(k+3) = a(k+2) + a(k) - 2*a(k+1). 
--    Написать функцию f :: Int -> Int. f i возвращает i элемент этой последовательности. f 
-- Должна работать линейное время. Нельзя использовать списки. Hint: используйте 
-- кортежи. 

ff :: Int -> Int
ff = helper (1, 2, 3) where
	helper (a, b, c) 0 = a
	helper (a, b, c) 1 = b
	helper (a, b, c) 2 = c
	helper (a, b, c) k = helper (b, c, c + a - 2 * b) (k - 1)
