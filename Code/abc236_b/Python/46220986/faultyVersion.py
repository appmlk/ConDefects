from collections import Counter

n = int(input())
A = input().replace(' ', '')

c = Counter(A)

print(c.most_common()[-1][0])
