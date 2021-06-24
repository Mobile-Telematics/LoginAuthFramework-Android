package com.telematics.auth.errors


/**
 * Detailed message about API-exception.
 *
 * Contains two parameters inside: field and detailed message.
 *
 * @param field on which field occurs an error
 * @param message detailed message about error
 */
class FieldErrorDetailsData(
        var field: String? = null
) : ErrorDetailsData()