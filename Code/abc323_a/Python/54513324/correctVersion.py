#https://atcoder.jp/contests/abc323/tasks/abc323_a

S = input()
flag = True
for i, s in enumerate(S):
    if (i + 1) % 2 == 0:
        # print((i + 1), s)
        if s == "0":
            continue
        else:
            flag = False
            break

if flag:
    print("Yes")
else:
    print("No")