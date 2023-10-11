N = int(input())
S = input()

cnt = 0
for i in S:
    if i == "T":
        cnt += 1

if cnt > N - cnt or (cnt == N - cnt and S[-1] == "A"):
    print("T")
else:
    print("A")