string = input()
start = string.find("|", 0)
end = string.find("|", start + 1) + 1
if not(start == (end - 2)): string = string.replace(string[start:end], "")
else: string = string.replace("|", "")
print(string)