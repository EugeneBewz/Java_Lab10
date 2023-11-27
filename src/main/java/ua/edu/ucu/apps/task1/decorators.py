"""
Looking up Pythonic decorators
to compare later with Java
"""


import time


def uppercase_decorator(function):
    def wrapper():
        func = function()
        make_uppercase = func.upper()
        return make_uppercase
    return wrapper


@uppercase_decorator
def say_hi():
    """
    Apply decorator to this function
    """
    return 'hello there'


print(say_hi())


def square(func):
    def wrapper(*args, **kwargs):
        res = func(*args, **kwargs)
        return res * res
    return wrapper


@square
def give_sum(x, y):
    """
    Apply `square` decorator on this function
    """
    return x + y


print(give_sum(5, 5))  # expected: 100 (result sum squared)


# =======================================================================================
# Generated @retry by ChatGPT
def retry(max_retries=3, delay=1):
    def decorator(func):
        def wrapper(*args, **kwargs):
            attempts = 0
            while attempts < max_retries:
                try:
                    result = func(*args, **kwargs)
                    return result
                except Exception as e:
                    print(f"Caught an exception: {e}")
                    attempts += 1
                    time.sleep(delay)
            raise RuntimeError(f"Function {func.__name__} exceeded maximum retry attempts.")
        return wrapper
    return decorator


# Example usage
@retry(max_retries=3, delay=2)
def example_function():
    import random
    if random.random() < 0.5:
        raise ValueError("Random failure")
    return "Success!"


final_result = example_function()
print(final_result)
