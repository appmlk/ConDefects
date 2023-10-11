N = int(input())

S = input()

max = 0
count = 0

for i in range(N):
    if S[i] == "o":
        count += 1
        if count > max:
            max = count
    else:
        count = 0

if max == len(S) and S[0] == "-" and S[len(S) - 1] == "-":
    print(-1)
elif max == 0:
    print(-1)
else:
    print(max)
