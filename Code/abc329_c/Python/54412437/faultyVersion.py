n = int(input())
lsts = list(input())
ords = [ord(x) - ord("a") for x in lsts]
apb = [0] * 26

apb[ords[0]] = 1
cnt = 1
for i in range(1,n):
    if i == n-1:
        apb[ords[i]] = max(cnt, apb[ords[i]])
        continue
    if ords[i] == ords[i+1]:
        cnt += 1
        continue
    elif ords[i] != ords[i+1]:
        apb[ords[i]] = max(cnt, apb[ords[i]])
        cnt = 1
        continue

print(sum(apb))