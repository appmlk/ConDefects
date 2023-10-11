from collections import Counter

n = int(input())
A = list(map(int, input().split()))

c = Counter(A)

print(c.most_common()[-1][0])
