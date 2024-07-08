S = input()

strgs = list(set(S))
strgs_count = []
results = []
for strg in strgs:
    strgs_count.append(S.count(strg))

for unique in list(set(strgs_count)):
    if strgs_count.count(unique) % 2 == 0:
        result = True
        results.append(result)
    else:
        result = False
        results.append(result)

if False in results:
    print('No')
else:
    print('Yes')