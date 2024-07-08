A, B = map(int, input().split())
degrees = []
at = 2
while at * at <= A:
    if A % at == 0:
        cnt = 0
        while A % at == 0:
            cnt += 1
            A //= at
        degrees.append(cnt)
    at += 1
if A != 1:
    degrees.append(1)
# print(*degrees)
new_degrees = []
for el in degrees:
    new_degrees.append(el)
for i in range(len(new_degrees)):
    new_degrees[i] *= B
# print(*new_degrees)
all_pr = 1
for el in new_degrees:
    all_pr *= (el + 1)
ANS = 10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 + 228
for i in range(len(new_degrees)):
    el = new_degrees[i]
    ANS = min(ANS,  (all_pr // (el + 1)) * (el * (1 + el) // 2) // degrees[i])
print(ANS % 998244353)
