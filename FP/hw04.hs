module Hw04 where

-- 1. 
b x y z = x (y z) 
k x y = x 
s x y z = x z (y z) 
-- ����������� b ����� s � k.. 

b' x y z = s (k x) y z


-- 2. 
plus = (+) 
-- �������� mult (���������) ����� plus. 
-- �������� expt (���������� � �������) ����� mult. 


mult :: Integer -> (Integer -> Integer)
mult a b = if a < 0 then multpos (-a) (-b) else multpos a b
	where multpos a b = if a > 0 then plus (multpos (a-1) b) b else 0

expt :: Integer -> (Integer -> Integer)
expt a b = if b > 0 then mult a (expt a (b-1)) else 1


-- 3. ����������� ��������� ������� http://en.wikipedia.org/wiki/McCarthy_91_function 
mc :: Integer -> Integer
mc n = if n > 100 then n - 10 else mc (mc (n + 11))

-- 4. ����������� �������� ����������� minp :: (Integer -> Bool) -> Integer 

minp :: (Integer -> Bool) -> Integer 
minp p = helper p 0
	where helper p n = if p n then n else helper p (n + 1)

-- 5. 
-- a) �������� �������, ������������ ���������� ���� �����.
ndig :: Integer -> Integer
ndig n = helper 0 n
	where helper acc n = if n > 0 then helper (acc + 1) (div n 10) else acc

-- b) �������� �������, ������������ ����� ���� �����. 
sumdig :: Integer -> Integer
sumdig n = helper 0 n
	where helper acc n = if n > 0 then helper (acc + (mod n 10)) (div n 10) else acc

-- 6. ����������� ������� gcd ��� ������ ��������� �������. 

gcd' :: Integer -> (Integer -> Integer)
gcd' a b = if b > 0 then gcd' b (mod a b) else a

-- 7. ����������� �������, ��������� �������� ������������� ��������� �� ������� ������� ��������. 

intl :: (Float -> Float) -> (Float -> (Float -> Float))
intl f a b = helper 0 f a b
	where delta = 0.001;
		helper acc f a b = if a < b then helper (acc + trap f a delta) f (a + delta) b else acc
			where trap f a delta' = ((f a) + (f (a + delta'))) / 2 * delta'
