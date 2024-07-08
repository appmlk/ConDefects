n, l, r = map(int, input().split())
a = list(map(int, input().split()))
b = [i % (l + r) for i in a]
g = [i // l for i in b]
ans = 0
for i in g:
    ans ^= i
if ans:
    print("First")
else:
    print("Second")