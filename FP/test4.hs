ddd f g = (\h -> h (g (\x -> h (f x))))(id)
