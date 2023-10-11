n, p, q = map(int, input().split(' '))
d = list(map(int, input().split(' ')))
print(min(p, q + min(d)))
