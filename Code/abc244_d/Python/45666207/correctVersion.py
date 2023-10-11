S = [a for a in input().split()]
T = [a for a in input().split()]

cnt = 0
for i in range(3):
    if S[i] == T[i]:
        cnt += 1

if cnt == 3 or cnt == 0:
    print("Yes")
else:
    print("No")