N = int(input())
S = input()
T = input()

T = T + " "

S_count = [0]*26
T_count = [0]*26
for i in range(N):
    S_count[ord(S[i])-ord('a')] += 1
    T_count[ord(T[i])-ord('a')] += 1

for i in range(26):
    if S_count[i] != T_count[i]:
        print(-1)
        exit()

cur = N-1
count = 0
for i in range(N-1, -1, -1):
    while S[i] != T[cur]:
        if cur != -1:
            break
        cur -= 1
    else:
        count += 1
        cur -= 1
print(N-count)