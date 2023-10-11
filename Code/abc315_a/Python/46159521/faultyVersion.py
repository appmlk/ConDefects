S = input()
List = list(S)

result = [i for i in List if i != 'a' and i != 'i' and i != 'u' and i != 'e' and i != 'o']

print(result)