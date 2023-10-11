# B
N, K = map(int, input().split())
S = input()
#N, K = 10, 3
#S = "oxxoxooxox"
#print(S)
cnt = 0
s = ""
for i in S:
    #print(i)
    if i == "o" and cnt < K:
        cnt += 1
        s = s + i
    elif i == "o":
        s = s + "x"
    if i == "x":
        s = s + "x"
print(s)