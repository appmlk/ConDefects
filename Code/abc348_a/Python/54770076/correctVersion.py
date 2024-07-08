n = int(input())

results = ""
for i in range(1, n + 1):
    if i % 3 == 0:
        results += "x"
    else:
        results += "o"


print(results)