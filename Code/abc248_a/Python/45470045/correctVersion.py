s = input()

A = [int(x) for x in s]

for i in range(10):
    if i not in A:
        print(i)
        exit()
