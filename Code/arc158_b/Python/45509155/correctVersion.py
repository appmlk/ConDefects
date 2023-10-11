n = int(input())
x = list(map(int, input().split()))
x.sort(key=lambda x: 1 / x)
cand = []


def calc(i, j, k):
    a, b, c = x[i], x[j], x[k]
    return (a + b + c) / (a * b * c)


cand.append(calc(0, 1, 2))
cand.append(calc(0, 1, -1))
cand.append(calc(0, -1, -2))
cand.append(calc(-1, -2, -3))
print(min(cand))
print(max(cand))
