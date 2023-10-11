S = list(map(int, input()))

if S[0]:
    exit(print("No"))
column = [S[6], S[3], S[1] | S[8], S[0] | S[4], S[2] | S[8], S[5], S[9]]
for i in range(7):
    for j in range(i + 1, 7):
        for k in range(j + 1, 7):
            if column[i] and not column[j] and column[k]:
                exit(print("Yes"))
print("No")
